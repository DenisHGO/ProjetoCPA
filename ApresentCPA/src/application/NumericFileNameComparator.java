package application;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericFileNameComparator implements Comparator<File> {

    private static Pattern patPrefixoNumerico = Pattern.compile ("([0-9]+)(.*)");
    private static int compareFilenames (String filename1, String filename2) {
        // Comparamos a parte numerica e a parte nao-numerica em separado
        Matcher matcher1 = patPrefixoNumerico.matcher(filename1);
        Matcher matcher2 = patPrefixoNumerico.matcher(filename2);
        if (matcher1.matches() && matcher2.matches()) {
            int ret = Long.valueOf(matcher1.group(1)).compareTo(Long.valueOf(matcher2.group(1)));
            if (ret != 0)
                return ret;
            else return matcher1.group(2).compareToIgnoreCase(matcher2.group(2));
        }
        // Se nao tiver prefixo numerico...
        return filename1.compareToIgnoreCase(filename2);
    }
    
    @Override
    public int compare(File f1, File f2) {
        if ((f1.getParent() != null && f2.getParent() != null && f1.getParent().equalsIgnoreCase(f2.getParent()))
                || (f1.getParent() == null && f2.getParent() == null))
            return compareFilenames (f1.getName(), f2.getName());
        else
            return f1.compareTo(f2);
    }
}