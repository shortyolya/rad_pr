/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.db.model.lotonline;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.SequenceGenerator;

import java.io.Serializable;

public class LotOnlineShard3SequnceGenerator extends SequenceGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        long sequenceGenerated = (Long) super.generate(session, object);
        Long result = (sequenceGenerated * 1000 + 3);
        if (result < sequenceGenerated) {
            //oops...  overflow
            throw new IllegalStateException("ID generation is overflowed");
        }
        return result;
    }
}
