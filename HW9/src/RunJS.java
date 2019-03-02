import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunJS {
    public static class IO {
        private final ScriptEngine engine;
        public IO(final ScriptEngine engine) {
            this.engine = engine;
        }

        public void print(final String message) {
            System.out.print(message);
        }

        public void println(final String message) {
            System.out.println(message);
        }

        public void include(final String file) throws Exception {
            engine.eval(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        }
    }

    public static void main(final String[] args) throws ScriptException, IOException {
        final ScriptEngineManager factory = new ScriptEngineManager();
        final ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.put("io", new IO(engine));
        engine.put("global", engine.getContext().getBindings(ScriptContext.ENGINE_SCOPE));
        engine.eval(new InputStreamReader(new FileInputStream("objectExpression.js"), "UTF-8"));
    }
}