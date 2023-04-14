

package user.xmlBinding;


import user.xmlBinding.SqlType;
import user.xmlBinding.Sqlmap;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Sqlmap createSqlmap() {
        return new Sqlmap();
    }

    public SqlType createSqlType() {
        return new SqlType();
    }

}
