/* POPULATE TABLE SUBJECTS */
insert into subjects (name,description) values ('ECONOMY-101', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dapibus lacus nec leo varius, ut viverra justo aliquet. Sed convallis neque id varius dictum. Interdum et malesuada fames ac ante ipsum primis in faucibus. Etiam augue quam, facilisis at eleifend in, vestibulum non libero. Pellentesque imperdiet enim et ligula ultrices pellentesque. Nulla facilisi. Morbi aliquet justo ut turpis posuere aliquam. Cras tempus sollicitudin viverra. Donec euismod lectus risus, in tempor mauris eleifend at. Vestibulum vel mi quis libero lacinia condimentum. In eget odio elementum, ullamcorper nulla sed, aliquet turpis. Curabitur aliquam egestas augue non tempus. Fusce et magna nec sem vulputate venenatis. Ut eleifend accumsan pellentesque.');
insert into subjects (name,description) values ('SOCIOLOGY-101', 'Cras ultrices porta pharetra. Phasellus ut sapien eget nunc ultrices commodo. Suspendisse sit amet felis arcu. Suspendisse fermentum sed velit sed vehicula. Cras imperdiet faucibus nisl vel convallis. Curabitur quis lacus felis. Etiam quis aliquet metus, consectetur laoreet justo. Mauris eu convallis orci, sed dapibus magna. Pellentesque nec congue elit. Sed sed odio a sem elementum pulvinar. Mauris non justo at ante imperdiet lobortis et nec risus. Quisque aliquet mollis felis. Sed aliquam diam ante, vitae ullamcorper purus imperdiet sit amet. Sed volutpat ipsum nisl, et facilisis lorem volutpat non. Vivamus hendrerit, dolor vel maximus cursus, mi elit consectetur augue, sit amet finibus enim est eu lectus. Suspendisse et pellentesque ex.');
insert into subjects (name,description) values ('MATHEMATICS-101', 'Nulla facilisi. Maecenas eleifend nisi eu lacus tincidunt posuere. Sed eu placerat arcu. Vivamus sapien turpis, auctor vel tincidunt facilisis, tincidunt sed velit. In molestie ex eget porta luctus. Maecenas diam ante, tempus non luctus ut, tincidunt eu urna. Morbi et sapien eget risus venenatis feugiat. Vestibulum vitae urna velit. Nam vitae pulvinar mauris. Sed eget velit lacinia, imperdiet nisl ac, gravida eros. Pellentesque mattis, nisi sed accumsan scelerisque, quam diam accumsan nibh, et sodales eros orci et nunc. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur vel congue elit, eget euismod felis. Maecenas diam nisi, elementum non metus et, finibus mattis purus.');
insert into subjects (name,description) values ('PHYSICS-101', 'Mauris quis felis risus. Nullam posuere quis mi sed aliquet. Suspendisse potenti. Phasellus imperdiet, ante eget interdum lobortis, libero metus vehicula odio, a molestie neque sem in leo. Mauris sollicitudin, tortor id dapibus volutpat, nulla libero cursus mauris, vitae ultrices dui velit eu nisi. Duis at rhoncus augue. Suspendisse rutrum cursus nisi, quis ornare leo. Aenean turpis nisi, imperdiet at purus sed, sodales egestas nisl. Curabitur vitae turpis vitae dui semper posuere. Vestibulum volutpat enim at placerat ullamcorper. Suspendisse bibendum leo dignissim scelerisque semper. Ut sollicitudin congue justo, eget laoreet nisl volutpat in.');
insert into subjects (name,description) values ('COMP-SCIENCE-101', 'Aliquam ac rutrum risus. Suspendisse orci magna, posuere ut varius a, scelerisque sed dui. Aenean tristique mollis orci quis varius. Suspendisse potenti. Mauris quis laoreet arcu, sed lobortis enim. Aliquam tortor leo, vestibulum vitae vehicula vel, laoreet eget purus. Ut tincidunt tincidunt nunc in aliquet. In ex lacus, feugiat in nisl quis, tincidunt tempor tellus. Maecenas rhoncus ipsum est.');

/* POPULATE TABLE PROFESSORS*/
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('12349','AA-123',true,'John','Smith');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('32459','AB-234',true,'Mark','Silverman');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('94679','DU-333',true,'Lucile','West');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('45329','CC-984',true,'Sarah','Sommers');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('98649','EN-123',true,'Peter','Grant');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('93329','LD-984',false,'Lara','Brown');
insert into professors (national_id,employee_id,is_active,first_name,last_name) values ('92349','VY-123',false,'Mathew','White');

/* POPULATE TABLE STUDENTS*/
insert into students (first_name,last_name,national_id) values ('Thomas','Black','123456');
insert into students (first_name,last_name,national_id) values ('Mara','Downing','234567');
insert into students (first_name,last_name,national_id) values ('Laura','Clearwater','345678');
insert into students (first_name,last_name,national_id) values ('Nick','White','223344');
insert into students (first_name,last_name,national_id) values ('William','Delano','123123');
insert into students (first_name,last_name,national_id) values ('Anne','Smith','999876');

/* POPULATE TABLE CLASSES*/
insert into courses (code,subject_id,time,day,max_capacity) values ('AA-111-01', 1, '9-11 AM', 'Mon-Thu', 2);
insert into courses (code,subject_id,time,day,max_capacity) values ('AB-121-02', 2, '1-3 PM', 'Mon-Wed-Thu', 50);
insert into courses (code,subject_id,time,day,max_capacity) values ('BB-102-03', 3, '9-11 AM', 'Tue-Fri-Sat', 50);
insert into courses (code,subject_id,time,day,max_capacity) values ('BD-101-01', 4, '3-5 PM', 'Tue-Fri', 15);
insert into courses (code,subject_id,time,day,max_capacity) values ('CA-100-02', 5, '11-1 AM/PM', 'Wed-Sat', 0);

/* ADD SOME PROFESSORS TO CLASSES */
insert into course_to_professor (course_id,professor_id) values (1,1);
insert into course_to_professor (course_id,professor_id) values (2,1);
insert into course_to_professor (course_id,professor_id) values (3,2);
insert into course_to_professor (course_id,professor_id) values (4,2);
insert into course_to_professor (course_id,professor_id) values (5,3);
insert into course_to_professor (course_id,professor_id) values (4,4);
insert into course_to_professor (course_id,professor_id) values (5,5);

/* ADD SOME STUDENTS TO CLASSES */
insert into course_to_student (course_id,student_id) values (1,1);
insert into course_to_student (course_id,student_id) values (1,2);
insert into course_to_student (course_id,student_id) values (2,1);
insert into course_to_student (course_id,student_id) values (2,2);
insert into course_to_student (course_id,student_id) values (3,3);
insert into course_to_student (course_id,student_id) values (3,4);
insert into course_to_student (course_id,student_id) values (4,5);
insert into course_to_student (course_id,student_id) values (4,5);

/* ADD SOME PROFESSORS TO SUBJECTS */ 
insert into subject_to_professor (professor_id,subject_id) values (1,1);
insert into subject_to_professor (professor_id,subject_id) values (2,5);
insert into subject_to_professor (professor_id,subject_id) values (3,2);
insert into subject_to_professor (professor_id,subject_id) values (4,3);
insert into subject_to_professor (professor_id,subject_id) values (5,4);
insert into subject_to_professor (professor_id,subject_id) values (1,2);
insert into subject_to_professor (professor_id,subject_id) values (2,3);
insert into subject_to_professor (professor_id,subject_id) values (3,1);
insert into subject_to_professor (professor_id,subject_id) values (6,4);
insert into subject_to_professor (professor_id,subject_id) values (7,5);





