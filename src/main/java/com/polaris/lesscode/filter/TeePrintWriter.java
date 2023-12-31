package com.polaris.lesscode.filter;

import java.io.PrintWriter;

/**
 * @author jeecg-boot
 * @version 1.0
 * @description 描述
 * @create 2019-11-25 17:00
 */
public class TeePrintWriter extends PrintWriter {

    PrintWriter branch;

    public TeePrintWriter(PrintWriter main, PrintWriter branch) {
        super(main, true);
        this.branch = branch;
    }

    @Override
    public void write(char buf[], int off, int len) {
        super.write(buf, off, len);
        super.flush();
        branch.write(buf, off, len);
        branch.flush();
    }

    @Override
    public void write(String s, int off, int len) {
        super.write(s, off, len);
        super.flush();
        branch.write(s, off, len);
        branch.flush();
    }

    @Override
    public void write(int c) {
        super.write(c);
        super.flush();
        branch.write(c);
        branch.flush();
    }

    @Override
    public void flush() {
        super.flush();
        branch.flush();
    }
}

