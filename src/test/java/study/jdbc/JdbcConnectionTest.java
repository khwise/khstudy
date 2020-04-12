package study.jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kh.jin on 2020. 3. 22.
 */

public class JdbcConnectionTest {
    JdbcConnection conn = null;

    @Before
    public void setup() {
        conn = new JdbcConnection();
    }

    @Test
    public void test_드라이브로딩_정상() {
        conn.connection();
    }

    @Test
    public void selectCount_데이터가없는경우() {
        //given
        int annuls = 2020;
        int expect = 0;
        //when
        int actual = conn.selectCount(annuls);
        //then
        assertEquals(expect, actual);
    }

    @Test
    public void selectCount_데이터가있는경우() {
        //given
        int annuls = 2018;
        int expect = 0;
        //when
        int actual = conn.selectCount(annuls);
        //then
        //assertEquals(expect, actual);
        //assertThat(true, Is.);
        assertTrue(expect < actual);
    }

    @Test
    public void selectMember(){

        String memberNm = "강";
        conn.selectMember(memberNm);



    }

    @Test
    public void insertMember() throws SQLException {
        conn.insertMember();
    }

    @Test
    public void updateMember() throws SQLException {
        conn.updateMember();
    }

    @Test
    public void deleteMember() throws SQLException{
        conn.deleteMember();
    }
}
