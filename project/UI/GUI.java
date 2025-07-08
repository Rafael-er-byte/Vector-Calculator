package project.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import project.application.dtos.NumberDto;
import project.application.dtos.VectorDto;
import project.application.interfaces.iOperationUseCases;
import project.application.interfaces.iVectorUseCases;

public class GUI extends Frame {

    private Choice menuOptions;
    private iOperationUseCases operationUseCases;
    private iVectorUseCases vectorUseCases;

    public GUI(iOperationUseCases operationUseCases, iVectorUseCases vectorUseCases) {
        super("Calculadora de vectores");
        this.operationUseCases = operationUseCases;
        this.vectorUseCases = vectorUseCases;
    }

    public void run() {
        setLayout(new FlowLayout());

        Label titleLabel = new Label("Selecciona una opción:");
        add(titleLabel);

        menuOptions = new Choice();
        menuOptions.add("1. Crear vector");
        menuOptions.add("2. Sumar vectores");
        menuOptions.add("3. Normalizar vector");
        menuOptions.add("4. Calcular magnitud");
        menuOptions.add("5. Calcular scalar");
        menuOptions.add("6. Calcular Proyeccion");
        menuOptions.add("7. Obtener valor maximo de un vector");
        menuOptions.add("8. Obtener valor minimo de un vector");
        menuOptions.add("9. Calcuar promedio");
        menuOptions.add("10. Calcular vector opuesto");
        menuOptions.add("11. Calcular sumatoria");
        menuOptions.add("12. Calcular producto punto");
        menuOptions.add("13. Ver mis vectores");
        menuOptions.add("14. Eliminar vector");
        menuOptions.add("15. Modificar vector");

        add(menuOptions);

        Button executeButton = new Button("Ejecutar");
        add(executeButton);

        Button exit = new Button("Salir");
        add(exit);

        exit.addActionListener(e -> System.exit(0));

        executeButton.addActionListener(e -> {
            String selected = menuOptions.getSelectedItem();
            switch (selected) {
                case "1. Crear vector":
                    showCreateVectorForm();
                    break;
                case "2. Sumar vectores":
                    showTwoVectorsForm("Sumar", operationUseCases::add);
                    break;
                case "3. Normalizar vector":
                    showSingleVectorInput("Normalizar", name -> {
                        try {
                            showVectorResult(operationUseCases.normalization(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "4. Calcular magnitud":
                    showSingleVectorInput("Magnitud", name -> {
                        try {
                            showNumberResult(operationUseCases.magnitude(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "5. Calcular scalar":
                    showVectorAndNumberForm("Escalar", operationUseCases::scalar);
                    break;
                case "6. Calcular Proyeccion":
                    showTwoVectorsForm("Proyección", operationUseCases::projection);
                    break;
                case "7. Obtener valor maximo de un vector":
                    showSingleVectorInput("Máximo", name -> {
                        try {
                            showNumberResult(operationUseCases.maximum(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "8. Obtener valor minimo de un vector":
                    showSingleVectorInput("Mínimo", name -> {
                        try {
                            showNumberResult(operationUseCases.minimum(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "9. Calcuar promedio":
                    showSingleVectorInput("Promedio", name -> {
                        try {
                            showNumberResult(operationUseCases.average(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "10. Calcular vector opuesto":
                    showSingleVectorInput("Opuesto", name -> {
                        try {
                            showVectorResult(operationUseCases.opposite(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "11. Calcular sumatoria":
                    showSingleVectorInput("Sumatoria", name -> {
                        try {
                            showNumberResult(operationUseCases.sumatory(new VectorDto(name)));
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "12. Calcular producto punto":
                    showTwoVectorsForm("Producto Punto", operationUseCases::dot);
                    break;
                case "13. Ver mis vectores":
                    showVectorList();
                    break;
                case "14. Eliminar vector":
                    showSingleVectorInput("Eliminar", name -> {
                        try {
                            vectorUseCases.deleteByName(new VectorDto(name));
                            showMessage("Vector eliminado exitosamente.");
                        } catch (Exception ex) {
                            showMessage("Error: " + ex.getMessage());
                        }
                    });
                    break;
                case "15. Modificar vector":
                    showCreateVectorForm();
                    break;
                default:
                    showMessage("Opción no implementada aún.");
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void showMessage(String message) {
        Frame resultFrame = new Frame("Resultado");
        resultFrame.setLayout(new BorderLayout());

        TextArea textArea = new TextArea(message, 10, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        textArea.setEditable(false);
        resultFrame.add(textArea, BorderLayout.CENTER);

        Button closeButton = new Button("Cerrar");
        closeButton.addActionListener(e -> resultFrame.dispose());
        Panel buttonPanel = new Panel();
        buttonPanel.add(closeButton);
        resultFrame.add(buttonPanel, BorderLayout.SOUTH);

        resultFrame.setSize(500, 300);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setVisible(true);

        resultFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                resultFrame.dispose();
            }
        });
    }

    private void showVectorList() {
        Frame listFrame = new Frame("Lista de Vectores");
        listFrame.setLayout(new GridLayout(0, 1));
        try {
            ArrayList<VectorDto> vectors = vectorUseCases.getAllVectors();
            for (VectorDto vec : vectors) {
                Panel panel = new Panel();
                panel.setBackground(Color.LIGHT_GRAY);
                panel.setLayout(new BorderLayout());
                panel.add(new Label(vec.getName() + " => " + vec.getVector()), BorderLayout.CENTER);
                listFrame.add(panel);
            }

            listFrame.setSize(500, 400);
            listFrame.setVisible(true);
            listFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    listFrame.dispose();
                }
            });
        } catch (Exception e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void showVectorResult(VectorDto result) {
        showMessage("Resultado (Vector): " + result.getVector());
    }

    private void showNumberResult(NumberDto result) {
        showMessage("Resultado (Número): " + result.getNum());
    }

    private void showCreateVectorForm() {
        Frame form = new Frame("Crear Vector");
        form.setLayout(new GridLayout(4, 1));

        TextField nameField = new TextField(20);
        TextField valuesField = new TextField(20);

        form.add(new Label("Nombre:"));
        form.add(nameField);
        form.add(new Label("Valores (separados por coma):"));
        form.add(valuesField);

        Button submit = new Button("Crear");
        form.add(submit);

        submit.addActionListener(e -> {
            try {
                String name = nameField.getText();
                String values = valuesField.getText();
                vectorUseCases.createVector(new VectorDto(name, values));
                showMessage("Vector creado exitosamente.");
                form.dispose();
            } catch (Exception ex) {
                form.dispose();
                showMessage("Error: " + ex.getMessage());
            }
        });

        form.setSize(400, 200);
        form.setLocationRelativeTo(null);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                form.dispose();
            }
        });
    }

    private void showTwoVectorsForm(String title, BiVectorOperation op) {
        Frame form = new Frame(title);
        form.setLayout(new GridLayout(6, 1));

        TextField name1Field = new TextField(20);
        TextField name2Field = new TextField(20);

        form.add(new Label("Nombre del primer vector:"));
        form.add(name1Field);
        form.add(new Label("Nombre del segundo vector:"));
        form.add(name2Field);

        Button submit = new Button("Ejecutar");
        form.add(submit);

        submit.addActionListener(e -> {
            try {
                String name1 = name1Field.getText();
                String name2 = name2Field.getText();
                Object result = op.apply(new VectorDto(name1), new VectorDto(name2));
                if (result instanceof VectorDto)
                    showVectorResult((VectorDto) result);
                else if (result instanceof NumberDto)
                    showNumberResult((NumberDto) result);
                form.dispose();
            } catch (Exception ex) {
                form.dispose();
                showMessage("Error: " + ex.getMessage());
            }
        });

        form.setSize(400, 250);
        form.setLocationRelativeTo(null);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                form.dispose();
            }
        });
    }

    private void showVectorAndNumberForm(String title, VectorAndNumberOperation op) {
        Frame form = new Frame(title);
        form.setLayout(new GridLayout(6, 1));

        TextField nameField = new TextField(20);
        TextField numberField = new TextField(20);

        form.add(new Label("Nombre del vector:"));
        form.add(nameField);
        form.add(new Label("Escalar:"));
        form.add(numberField);

        Button submit = new Button("Ejecutar");
        form.add(submit);

        submit.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double num = Double.parseDouble(numberField.getText());
                VectorDto result = op.apply(new VectorDto(name), new NumberDto(num));
                showVectorResult(result);
                form.dispose();
            } catch (NumberFormatException ex) {
                form.dispose();
                showMessage("Error: El escalar debe ser un número.");
            } catch (Exception ex) {
                form.dispose();
                showMessage("Error: " + ex.getMessage());
            }
        });

        form.setSize(400, 250);
        form.setLocationRelativeTo(null);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                form.dispose();
            }
        });
    }

    private void showSingleVectorInput(String title, java.util.function.Consumer<String> action) {
        Frame form = new Frame(title);
        form.setLayout(new GridLayout(4, 1));

        TextField nameField = new TextField(20);

        form.add(new Label("Nombre del vector:"));
        form.add(nameField);

        Button submit = new Button("Ejecutar");
        form.add(submit);

        submit.addActionListener(e -> {
            try {
                String name = nameField.getText();
                action.accept(name);
                form.dispose();
            } catch (Exception ex) {
                form.dispose();
                showMessage("Error: " + ex.getMessage());
            }
        });

        form.setSize(400, 180);
        form.setLocationRelativeTo(null);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                form.dispose();
            }
        });
    }

    @FunctionalInterface
    interface BiVectorOperation {
        Object apply(VectorDto v1, VectorDto v2) throws Exception;
    }

    @FunctionalInterface
    interface VectorAndNumberOperation {
        VectorDto apply(VectorDto vec, NumberDto num) throws Exception;
    }
}
