package com.baltinfo.radius.db.model.lotonline;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * User: MZubkova
 * Date: 20.08.12
 */
public class TwoDimensionalStringArrayType implements UserType {

    private static final int[] SQL_TYPES = new int[]{Types.ARRAY};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return String[][].class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return (x == y) || (x != null && y != null && (x.equals(y)));
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override     
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object o)  throws HibernateException, SQLException {
        Array sqlArray = rs.getArray(names[0]);
        if(sqlArray != null){
            return sqlArray.getArray();
        }
        return null;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor si) throws HibernateException, SQLException {
       if (value == null) {
            st.setNull(index, Types.ARRAY);
        } else {
            Array array =  st.getConnection().createArrayOf("varchar", (String[][]) value);
            st.setArray(index, array);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (value == null) {
            return null;
        }
        String[][] array = (String[][]) value;
        String[][] copy = new String[array.length][];
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }


    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    


}
