package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.lang.reflect.ParameterizedType;
import javax.swing.table.DefaultTableModel;
import connection.ConnectionFactory;

/**
 * The type Generic dao.
 *
 * @param <T> the type parameter
 */
@SuppressWarnings("unchecked")
public class GenericDAO<T> {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(GenericDAO.class.getName());

    private final Class<T> type;

    /**
     * Instantiates a new Generic dao.
     */
    public GenericDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Insert.
     *
     * @param genericObject the generic object
     */
    public void Insert(Object genericObject) {
        String tableName = genericObject.getClass().getSimpleName();
        StringBuilder query = new StringBuilder();
        query.append("Insert into warehouse.").append(tableName).append(" (");
        Field[] allFields = genericObject.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                query.append(allFields[i].getName());
                query.append(", ");
            }
            allFields[allFields.length - 1].setAccessible(true);
            query.append(allFields[allFields.length - 1].getName());
            query.append(") values (?,?,?,? )");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at inserting");
        }
        PreparedStatement insertStatement = null;
        try {
            Connection con = ConnectionFactory.getConnection();
            insertStatement = con.prepareStatement(String.valueOf(query), Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            if (type != null) {
                for (Field field : type.getDeclaredFields()) {
                    Object value = field.get(genericObject);
                    insertStatement.setString(i, String.valueOf(value));
                    i++;
                }
                insertStatement.executeUpdate();
            }
        } catch (SQLException e) { LOGGER.log(Level.WARNING, "AbstractDao: insert " + e.getMessage()); } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {ConnectionFactory.close(insertStatement); }
    }
    public void M2(String q) {
        try {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement prepInsertStatement = con.prepareStatement(q);
            prepInsertStatement.executeUpdate();
            con.close();
            prepInsertStatement.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Exception when executing insert query");
        }
    }
    /**
     * Create table j table.
     *
     * @return the j table
     */
    public JTable createTable() {
        List<?> myList =findAll();
        if (!myList.isEmpty()) {
            int tableSize = myList.get(0).getClass().getDeclaredFields().length;
            String[] columnNames = getStrings(myList, tableSize);
            DefaultTableModel myModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;// all cells false
                }
            };
            myModel.setColumnIdentifiers(columnNames);
            set(myList, tableSize, myModel);
            JTable orderTable = new JTable(myModel);
            orderTable.setEnabled(true);
            orderTable.setVisible(true);
            return orderTable;
        }
        return null;
    }

    private void set(List<?> myList, int tableSize, DefaultTableModel myModel) {
        for (Object o : myList) {
            Object[] obj = new Object[tableSize];
            int col = 0;
            for (Field currentField : o.getClass().getDeclaredFields()) {
                currentField.setAccessible(true);
                try {
                    obj[col] = currentField.get(o);
                    col++;
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            myModel.addRow(obj);
        }
    }

    private String[] getStrings(List<?> myList, int tableSize) {
        String[] columnNames = new String[tableSize];
        int columnIndex = 0;
        for (Field currentField : myList.get(0).getClass().getDeclaredFields()) {
            currentField.setAccessible(true);
            try {
                columnNames[columnIndex] = currentField.getName();
                columnIndex++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return columnNames;
    }

    /**
     * Delete.
     *
     * @param genericObject the generic object
     */
    public void delete(Object genericObject) {
        String tableName = genericObject.getClass().getSimpleName();
        StringBuilder query = new StringBuilder();
        query.append("Delete from warehouse.").append(tableName).append(" where ");
        Field[] allFields = genericObject.getClass().getDeclaredFields();
        m1(genericObject, query, allFields);
        M2(query.toString());
    }

    /**
     * Update.
     *
     * @param genericObject the generic object
     */
    public void update(Object genericObject) {
        String tableName = genericObject.getClass().getSimpleName();
        StringBuilder query = new StringBuilder();
        query.append("update warehouse.").append(tableName).append(" set ");
        Field[] allFields = genericObject.getClass().getDeclaredFields();
        extracted(genericObject, query, allFields);
        int lastFieldIndex = allFields.length - 1;
        allFields[lastFieldIndex].setAccessible(true);
        query.append(allFields[lastFieldIndex].getName());
        query.append(" = ");
        extracted(genericObject, query, allFields[lastFieldIndex]);

        query.append(" where ");
        m1(genericObject, query, allFields);
        M2(query.toString());
    }

    private void extracted(Object genericObject, StringBuilder query, Field allFields) {
        try {

            Object value = allFields.get(genericObject);
            String fieldType = allFields.getType().getSimpleName();
            if (fieldType.equals("String"))
                query.append("\"").append(value).append("\"");
            else
                query.append(value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at updating");
        }
    }

    private void extracted(Object genericObject, StringBuilder query, Field[] allFields) {
        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                query.append(allFields[i].getName());
                query.append(" = ");
                Object value = allFields[i].get(genericObject);
                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    query.append("\"").append(value).append("\"");
                else
                    query.append(value);
                query.append(", ");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at updating");
        }
    }

    private void m1(Object genericObject, StringBuilder query, Field[] allFields) {
        Field firstField = allFields[0];
        firstField.setAccessible(true);
        String fieldName = firstField.getName();
        query.append(fieldName).append(" = ");
        try {
            Object value = firstField.get(genericObject);
            query.append(value);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error at geting id value");
        }
    }

    private String createSelectQuery() {
        return "SELECT " +  " * " +
                " FROM warehouse." +
                type.getSimpleName() +
                " WHERE " + "id" + " =?";
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<T> findAll()  {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sb = "SELECT * FROM warehouse." +
                    type.getSimpleName();
            statement = connection.prepareStatement(sb);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
        LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
    } finally {
        ConnectionFactory.close(resultSet);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }
        return null;
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
           return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                assert ctor != null;
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalArgumentException | SecurityException | IllegalAccessException | IntrospectionException | SQLException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

}
