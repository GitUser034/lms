//package com.Lms.LeaveManagementSystem.utility;
//
//import com.Lms.LeaveManagementSystem.entity.Department;
//import com.Lms.LeaveManagementSystem.entity.Role;
//import com.Lms.LeaveManagementSystem.entity.User;
//import com.Lms.LeaveManagementSystem.repository.DepartmentRepository;
//import com.Lms.LeaveManagementSystem.repository.RoleRepository;
//import com.Lms.LeaveManagementSystem.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//      private final UserRepository userRepository;
//      private final DepartmentRepository departmentRepository;
//      private final RoleRepository roleRepository; // Add Role repository
//      private final PasswordEncoder passwordEncoder;
//
//      public DataInitializer(UserRepository userRepository,
//                             DepartmentRepository departmentRepository,
//                             RoleRepository roleRepository,
//                             PasswordEncoder passwordEncoder) {
//            this.userRepository = userRepository;
//            this.departmentRepository = departmentRepository;
//            this.roleRepository = roleRepository;
//            this.passwordEncoder = passwordEncoder;
//      }
//
//      @Override
//      @Transactional
//      public void run(String... args) throws Exception {
//
//            if (userRepository.count() > 0 || departmentRepository.count() > 0 || roleRepository.count() > 0) {
//                  System.out.println("DataInitializer: skipping sample data");
//                  return;
//            }
//
//            // Create roles first
//            Role managerRole = new Role();
//            managerRole.setRoleName("ROLE_MANAGER");
//
//            Role employeeRole = new Role();
//            employeeRole.setRoleName("ROLE_EMPLOYEE");
//
//            roleRepository.save(managerRole);
//            roleRepository.save(employeeRole);
//
//            // Create Managers
//            User engManager = new User();
//            engManager.setName("Alia Manager");
//            engManager.setEmail("alia.manager@example.com");
//            engManager.setPassword(passwordEncoder.encode("password123"));
//            engManager.setRole(managerRole);
//            engManager.setTotalLeaves(30);
//            engManager.setLeavesTaken(0);
//
//            User hrManager = new User();
//            hrManager.setName("Kiara Manager");
//            hrManager.setEmail("kiara.manager@example.com");
//            hrManager.setPassword(passwordEncoder.encode("password123"));
//            hrManager.setRole(managerRole);
//            hrManager.setTotalLeaves(30);
//            hrManager.setLeavesTaken(0);
//
//            userRepository.save(engManager);
//            userRepository.save(hrManager);
//
//            // Create Departments
//            Department eng = new Department();
//            eng.setName("Engineering");
//            //eng.setManager(engManager);
//
//            Department hr = new Department();
//            hr.setName("Human Resources");
//            //hr.setManager(hrManager);
//
//            departmentRepository.save(eng);
//            departmentRepository.save(hr);
//
//            // Set department for managers after departments are saved (to avoid transient errors)
//            engManager.setRole(managerRole);
//            engManager.setDepartment(eng);
//            hrManager.setRole(managerRole);
//            hrManager.setDepartment(hr);
//            userRepository.save(engManager);
//            userRepository.save(hrManager);
//
//            // Create Employees
//            User dev1 = new User();
//            dev1.setName("Alok Dev");
//            dev1.setEmail("alok.dev@example.com");
//            dev1.setPassword(passwordEncoder.encode("password123"));
//            dev1.setRole(employeeRole);
//            dev1.setTotalLeaves(20);
//            dev1.setLeavesTaken(0);
//            dev1.setDepartment(eng);
//
//            User hrStaff = new User();
//            hrStaff.setName("Mrunal HR");
//            hrStaff.setEmail("mrunal.hr@example.com");
//            hrStaff.setPassword(passwordEncoder.encode("password123"));
//            hrStaff.setRole(employeeRole);
//            hrStaff.setTotalLeaves(20);
//            hrStaff.setLeavesTaken(0);
//            hrStaff.setDepartment(hr);
//
//            userRepository.save(dev1);
//            userRepository.save(hrStaff);
//
//            System.out.println("Roles, Departments, and Users created.");
//      }
//}

