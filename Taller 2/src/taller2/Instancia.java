package taller2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Calendar;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Instancia {

    public static Ascensor newAscensor() {
        try {
            return (Ascensor) newInstancia(Ascensor.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(
                    "No se pudo crear la instancia de la clase ", e);
        }
    }

    private static Object newInstancia(Class clase) throws Exception {

        File src = new File("src");
        File f = new File(src, clase.getCanonicalName().replaceAll("\\.", "/") + "Proxy.java");

        StringBuilder sb = new StringBuilder();
        sb.append("package " + clase.getPackage().getName() + ";");
        sb.append("public class " + clase.getSimpleName() + "Proxy extends "
                + clase.getSimpleName() + "{");
        
        
        
        for (Method method : clase.getDeclaredMethods()) {
            if (method.getAnnotations() != null) {
                /*Punto 1 del taller*/
                if (method.getAnnotation(LoggerAnnotation.class) != null) {
                    //Metodo Auxiliar
                    createLog(method.getName());
                }
            }
        }
        
        for (Method method : clase.getDeclaredMethods()) {
            if (method.getAnnotations() != null) {
                /*Punto 2 del taller*/
                if (method.getAnnotation(PostConstructorAnnotation.class) != null) {
                    
                    System.out.println("Metodo: "+ method
                            + " instanciado despues de constructor(?)");
                }
            }
        }
        
        for (Method metodo : clase.getDeclaredMethods()) {
            if (metodo.getAnnotations() != null) {
                sb.append(modifierFromString(metodo.getModifiers()) + " "
                        + metodo.getReturnType().getName() + " "
                        + metodo.getName() + "(");
                for (Parameter parametro : metodo.getParameters()) {
                    sb.append(parametro.getType().getName() + " "
                            + parametro.getName());
                }
                sb.append("){");

                if (metodo.getAnnotation(InvocacionMultiple.class) != null) {
                    InvocacionMultiple anotacion = metodo.getAnnotation(
                            InvocacionMultiple.class);

                    for (int i = 0; i < anotacion.vecesAInvocar() - 1; i++) {
                        sb.append("super." + metodo.getName() + "(");
                        for (Parameter parametro : metodo.getParameters()) {
                            sb.append(parametro.getName());
                        }
                        sb.append(");");
                    }

                    sb.append("super." + metodo.getName() + "(");
                    for (Parameter parametro : metodo.getParameters()) {
                        sb.append(parametro.getName());
                    }
                    sb.append(");");
                }
                sb.append("}");
            }
        }
        
        sb.append("}");

        FileWriter fw = new FileWriter(f);
        fw.write(sb.toString());
        fw.flush();
        fw.close();
        
        
        
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, f.getPath());

        URLClassLoader classLoader = URLClassLoader.newInstance(
                new URL[]{src.toURI().toURL()});
        Class<?> cls = Class.forName(clase.getCanonicalName() + "Proxy",
                true, classLoader);
        
        
        
        
        f.delete();
        new File(src, clase.getCanonicalName().replaceAll("\\.", "/")
                + "Proxy.class").delete();

        return cls.newInstance();
    }

    /*
        *Auxiliar del punto 1 del taller
     */
    private static void createLog(String operation) throws IOException {
        File fileLog = new File("Log.txt");
        if (fileLog.exists() == false) {
            FileWriter fileLogWrite = new FileWriter(fileLog, false);
        }
        FileWriter fileLogWrite = new FileWriter(fileLog, true);
        Calendar actualDate = Calendar.getInstance();
        fileLogWrite.write(
                "Fecha: "
                + (String.valueOf(actualDate.get(Calendar.DAY_OF_MONTH))
                + "/" + String.valueOf(actualDate.get(Calendar.MONTH) + 1)
                + "/" + String.valueOf(actualDate.get(Calendar.YEAR))
                + ", Hora: "
                + String.valueOf(actualDate.get(Calendar.HOUR_OF_DAY))
                + ":" + String.valueOf(actualDate.get(Calendar.MINUTE))
                + ":" + String.valueOf(actualDate.get(Calendar.SECOND)))
                + ", Nombre de operacion: " + operation + "\r\n");
        fileLogWrite.flush();
        fileLogWrite.close();
        System.out.println("Path del archivo Log: "+ fileLog.getAbsolutePath());
    }

    private static String modifierFromString(int m) {
        // TODO Auto-generated method stub

        switch (m) {
            case Modifier.PUBLIC:
                return "public";
            case Modifier.PROTECTED:
                return "protected";
            case Modifier.PRIVATE:
                return "private";
            case Modifier.STATIC:
                return "static";
            case Modifier.FINAL:
                return "final";
            case Modifier.TRANSIENT:
                return "transient";
            case Modifier.VOLATILE:
                return "volatile";

        }
        return null;
    }
}
