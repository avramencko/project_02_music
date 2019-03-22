package by.avramenko.app;

import by.avramenko.app.Exceptions.IncorrectDirectoryException;


public class Main {
    public static void main(String[] args) throws IncorrectDirectoryException {
        String[] path = args;
        Cataloger cataloger = new Cataloger(path);
        cataloger.parseDirectories();
        cataloger.generateHTML(path[0],"index");
        cataloger.generateHashCodeDuplicateList(path[0],"duplicates_1");
        cataloger.generateNameDuplicateList(path[0],"duplicates_2");
    }
}
