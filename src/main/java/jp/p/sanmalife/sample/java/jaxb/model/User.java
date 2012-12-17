package jp.p.sanmalife.sample.java.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    public String name;
    public int age;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        if (other.name.equals(name) && other.age == age) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
