package com.satria.fs.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import com.satria.fs.data.EmployeeMapper;
import com.satria.fs.dto.GetEmployeesResponseDto;
import com.satria.fs.dto.EmployeeRequestDto;
import com.satria.fs.model.Employee;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final String DB_CONFIG_XML = "com/satria/fs/data/db-config.xml";
    
    @Override
    public String postEmployee(EmployeeRequestDto employeeRequest) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(DB_CONFIG_XML);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        String errorDesc = null;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee isExist = mapper.selectEmployee(null, employeeRequest.getEmployeeNumber());
            if (isExist != null) {
                session.close();
                return "Employee number already registered.";
            }

            int rowsAffected = mapper.insertEmployee(
                employeeRequest.getEmployeeNumber(),
                employeeRequest.getPosition(),
                employeeRequest.getName(),
                employeeRequest.getPhoneNumber(),
                employeeRequest.getEmailAddress()
            );

            if (rowsAffected > 0) session.commit();
            else errorDesc = "Unkonwn error.";

            session.close();
        } catch (Exception e) {
            errorDesc = "Unknown error.";
            System.out.println(e);
        }

        return errorDesc;
    }

    @Override
    public String putEmployee(Long id, EmployeeRequestDto employeeRequest) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(DB_CONFIG_XML);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        String errorDesc = null;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            Employee isExist = mapper.selectEmployee(id, null);
            if (isExist == null) {
                session.close();
                return "Employee not found.";
            }

            int rowsAffected = mapper.updateEmployee(
                id,
                employeeRequest.getEmployeeNumber(),
                employeeRequest.getPosition(),
                employeeRequest.getName(),
                employeeRequest.getPhoneNumber(),
                employeeRequest.getEmailAddress()
            );

            if (rowsAffected > 0) session.commit();
            else errorDesc = "Unknown error.";

            session.close();
        } catch (Exception e) {
            errorDesc = "Unknown error.";
            System.out.println(e);
        }

        return errorDesc;
    }

    @Override
    public void deleteEmployee(Long id) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(DB_CONFIG_XML);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            mapper.deleteEmployee(id);
            session.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public GetEmployeesResponseDto getEmployees(HttpServletRequest httpRequest) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(DB_CONFIG_XML);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        List<Employee> employees = new ArrayList<>();

        int totalRows = 0;
        int totalPage = 1;
        int limit = httpRequest.getParameter("size") != null 
            ? Integer.parseInt(httpRequest.getParameter("size"))
            : 10;
        int currentPage = httpRequest.getParameter("page") != null
            ? Integer.valueOf(httpRequest.getParameter("page"))
            : 1;

        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            totalRows = mapper.selectCountEmployees(
                httpRequest.getParameter("employeeNumber") != null
                    ? Integer.parseInt(httpRequest.getParameter("employeeNumber"))
                    : null,
                httpRequest.getParameter("position"),
                httpRequest.getParameter("name"),
                httpRequest.getParameter("phoneNumber"),
                httpRequest.getParameter("email")
            );

            if (totalRows == 0) return new GetEmployeesResponseDto(
                totalRows, 1, totalPage, employees
            );

            totalPage = (totalRows / limit) + 1;
            employees = mapper.selectEmployees(
                httpRequest.getParameter("employeeNumber") != null
                    ? Integer.parseInt(httpRequest.getParameter("employeeNumber"))
                    : null,
                httpRequest.getParameter("position"),
                httpRequest.getParameter("name"),
                httpRequest.getParameter("phoneNumber"),
                httpRequest.getParameter("email"),
                httpRequest.getParameter("size") != null
                    ? Integer.parseInt(httpRequest.getParameter("size"))
                    : null,
                limit * (currentPage - 1)
            );
        } catch (Exception e) {
            System.out.println(e);
        }

        return new GetEmployeesResponseDto(
            totalRows,
            currentPage,
            totalPage,
            employees
        );
    }

}
