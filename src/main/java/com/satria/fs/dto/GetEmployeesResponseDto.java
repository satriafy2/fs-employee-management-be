package com.satria.fs.dto;

import java.util.List;

import com.satria.fs.model.Employee;

public class GetEmployeesResponseDto {
    private Integer rowsCount;
    private Integer currentPage;
    private Integer totalPage;
    private List<Employee> data;

    GetEmployeesResponseDto () {}

    public Integer getCurrentPage() {
        return currentPage;
    }

    public List<Employee> getData() {
        return data;
    }

    public Integer getRowsCount() {
        return rowsCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setData(List<Employee> data) {
        this.data = data;
    }

    public void setRowsCount(Integer rowsCount) {
        this.rowsCount = rowsCount;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public GetEmployeesResponseDto(
        Integer rowsCount,
        Integer currentPage,
        Integer totalPage,
        List<Employee> data
    ) {
        this.rowsCount = rowsCount;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.data = data;
    }
}
