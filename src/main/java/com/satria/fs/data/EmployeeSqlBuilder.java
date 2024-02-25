package com.satria.fs.data;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlBuilder implements ProviderMethodResolver{
    public String selectEmployee(Long id, Integer employeeNumber) {
        return new SQL(){{
            SELECT("*");
            FROM("employee");
            WHERE("is_active=true");
            if (id != null) WHERE("id=#{id}");
            if (employeeNumber != null) WHERE("employee_number=#{employeeNumber}");
        }}.toString();
    }

    public String selectEmployees(
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email,
        Integer limit,
        Integer offset
    ) {
        return new SQL(){{
            SELECT("*");
            FROM("employee");
            WHERE("is_active=true");
            if (employeeNumber != null) WHERE("employee_number = #{employeeNumber}");
            if (position != null) WHERE("position LIKE '%' || #{position} || '%'");
            if (name != null) WHERE("name LIKE '%' || #{name} || '%'");
            if (phoneNumber != null) WHERE("phone_number LIKE '%' || #{phoneNumber} || '%'");
            if (email != null) WHERE("email LIKE '%' || #{email} || '%'");
            ORDER_BY("name");

            if (limit == null) LIMIT(10);
            else LIMIT("#{limit}");

            if (offset == null) OFFSET(0);
            else OFFSET("#{offset}");
        }}.toString();
    }

    public String selectCountEmployees(
        Integer employeeNumber,
        String position,
        String name,
        String phoneNumber,
        String email
    ) {
        return new SQL(){{
            SELECT("COUNT(*)");
            FROM("employee");
            WHERE("is_active=true");
            if (employeeNumber != null) WHERE("employee_number = #{employeeNumber}");
            if (position != null) WHERE("position LIKE '%' || #{position} || '%'");
            if (name != null) WHERE("name LIKE '%' || #{name} || '%'");
            if (phoneNumber != null) WHERE("phone_number LIKE '%' || #{phoneNumber} || '%'");
            if (email != null) WHERE("email LIKE '%' || #{email} || '%'");
        }}.toString();
    }

    public String insertEmployee() {
        return new SQL(){{
            INSERT_INTO("employee");
            VALUES(
                "employee_number, position, name, phone_number, email_address, is_active",
                "#{employeeNumber}, #{position}, #{name}, #{phoneNumber}, #{email}, true"
            );
        }}.toString();
    }

    public String updateEmployee() {
        return new SQL(){{
            UPDATE("employee");
            SET(
                "employee_number=#{employeeNumber}",
                "position=#{position}",
                "name=#{name}",
                "phone_number=#{phoneNumber}",
                "email_address=#{email}"
            );
            WHERE("id=#{id}");
        }}.toString();
    }

    public String deleteEmployee(Long id) {
        return new SQL(){{
            UPDATE("employee");
            SET("is_active=false");
            WHERE("id=#{id}");
        }}.toString();
    }
}
