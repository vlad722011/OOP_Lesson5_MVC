package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private UserMapper mapper = new UserMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<User> getAllUsers() {
        List<String> lines = fileOperation.readAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.map(line));
        }
        return users;
    }

    @Override
    public String CreateUser(User user) {
        List<User> users = getAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        List<String> lines = mapToString(users);
        fileOperation.saveAllLines(lines);
        return id;
    }

    private List<String> mapToString(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(mapper.map(item));
        }
        return lines;
    }

    @Override
    public User updateUser(User user) {
        List<User> users = getAllUsers();
        for (User currentUser : users) {
            if (currentUser.getId().equals(user.getId())) {
                currentUser.setFirstName(user.getFirstName());
                currentUser.setLastName(user.getLastName());
                currentUser.setPhone(user.getPhone());
                currentUser.setId(user.getId());
            }
        }
        fileOperation.saveAllLines(mapToString(users));
        return user;
    }

    @Override
    public User deleteUser(User user) {
        List<User> users = getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            int currentUserId = i + 1;
            if (users.get(i).getId().equals(user.getId())) {
                int deletedUserId = Integer.parseInt(users.get(i).getId());
                users.remove(users.get(i));
                currentUserId = deletedUserId;
            }
            if (!(currentUserId == users.size() + 1)) {
                String curUserId = String.format("%d", currentUserId);
                users.get(i).setId(curUserId);
                currentUserId += 1;
            }
        }
        fileOperation.saveAllLines(mapToString(users));
        return user;
    }
}
