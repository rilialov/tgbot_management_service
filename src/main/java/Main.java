import management_service.entity.Report;
import users_service.UserDTO;
import users_service.UsersService;
import users_service.UsersServiceImplService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        UsersService usersService = new UsersServiceImplService().getPort(UsersService.class);

        UserDTO userDTO = usersService.getUserByNick("ultricies");

        System.out.println(userDTO.getFirstName());


        //today 20:00 get all todays trackings

        //get user and summ all todays trackings

        //new report today, set user
        Report report = new Report(LocalDate.now(), userDTO.getChatId());

        //set full time
        report.setFullTime(123);
    }
}
