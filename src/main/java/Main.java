import users_service.UserDTO;
import users_service.UsersService;
import users_service.UsersServiceImplService;

public class Main {
    public static void main(String[] args) {
        UsersService usersService = new UsersServiceImplService().getPort(UsersService.class);

        UserDTO userDTO = usersService.getUserByNick("ultricies");

        System.out.println(userDTO.getFirstName());
    }
}
