package modern.ch1;

import java.io.File;

public class FileFilter {
    public static void main(String[] args) {
        // 객체 참조
        File[] hiddenFiles = new File(".").listFiles(new java.io.FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        for (File hiddenFile : hiddenFiles) {
            System.out.println("hiddenFile.getName() = " + hiddenFile.getName());
        }

        File[] hiddenFiles2 = getHiddenFiles();

        for (File hiddenFile2 : hiddenFiles2) {
            System.out.println("hiddenFile2.getName() = " + hiddenFile2.getName());
        }
    }

    public static File[] getHiddenFiles() {
        // 메서드 참조, 메서드가 일급 값이 됨
        return new File(".").listFiles(File::isHidden);
    }
}
