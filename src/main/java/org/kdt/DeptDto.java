package org.kdt;

public class DeptDto {
    String deptno;
    String dname;
    String loc;

    @Override
    public String toString() {
        return "DeptDto{" +
                "deptno='" + deptno + '\'' +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
