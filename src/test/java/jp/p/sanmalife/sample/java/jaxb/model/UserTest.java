package jp.p.sanmalife.sample.java.jaxb.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private static final String USER_STR = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><id>0001</id><name>Tom</name><age>15</age></user>";
    private Marshaller m;
    private Unmarshaller um;
    private User tom;

    @Before
    public void 初期化() throws Exception {
        JAXBContext ctx = JAXBContext.newInstance(User.class);
        m = ctx.createMarshaller();
        um = ctx.createUnmarshaller();

        tom = new User();
        tom.id = "0001";
        tom.name = "Tom";
        tom.age = 15;
    }

    @Test
    public void ユーザオブジェクトをMarshalize出来る() throws Exception {
        StringWriter sw = new StringWriter();
        try {
            m.marshal(tom, sw);
            String userStr = sw.toString();
            assertThat(userStr, is(USER_STR));
        } finally {
            sw.close();
        }
    }

    @Test
    public void ユーザオブジェクトをUnmarshalize出来る() throws Exception {
        StringReader sr = new StringReader(USER_STR);
        User user = (User) um.unmarshal(sr);
        assertThat(user, is(tom));
    }

    @Test
    public void nullオブジェクトはmarshalizeされない() throws Exception {
        StringWriter sw = new StringWriter();
        User u = new User();

        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><age>0</age></user>";
        try {
            m.marshal(u, sw);
            String str = sw.toString();
            assertThat(str, is(expect));
        } finally {
            sw.close();
        }
    }

    @Test
    public void 空文字列は空要素としてmarshalizeされる() throws Exception {
        StringWriter sw = new StringWriter();
        User user = new User();
        user.name = "";

        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><name /><age>0</age></user>";
        try {
            m.marshal(user, sw);
            String result = sw.toString();
            assertThat(result, is(expect));
        } finally {
            sw.close();
        }

    }

}
