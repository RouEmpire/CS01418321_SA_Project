package administration.services;

import administration.models.All_List;
import administration.models.Employee;
import administration.models.Production;

import java.io.*;

public class LoginDataSource implements Datasource{
    private String fileDirectoryName;
    private String fileName;
    All_List all_list = new All_List();

    public LoginDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }
    private void readData1() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            try {
                Employee employee = new Employee(data[0].trim(), data[1].trim());
                all_list.addEmpolyee(employee);

            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Exception occurred: " + ex);
            }
        }
    }
    private void readData2() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            try {
                Production production = new Production(data[0].trim(), data[1].trim());
                all_list.addProduction(production);
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Exception occurred: " + ex);
            }
        }
    }


    @Override
    public All_List getFileData1() {
        try {
            all_list = new All_List();
            readData1();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return all_list;
    }

    @Override
    public All_List getFileData2() {
        try {
            all_list = new All_List();
            readData2();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }
        return all_list;
    }


}
