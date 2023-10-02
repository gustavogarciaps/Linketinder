import br.com.linketinder.database.ConnectionFactory
import org.junit.jupiter.api.Test

class ConnectionFactoryTest {

    @Test
    void testarConexao() {

        def connectionFactory = new ConnectionFactory()

        def sql = connectionFactory.newInstance()

        try {
            def rows = sql.rows("SELECT * FROM usuarios")
            println rows.join('\n')
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            sql.close()
        }
    }
}
