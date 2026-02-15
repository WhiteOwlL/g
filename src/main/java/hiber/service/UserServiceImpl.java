package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;   // нужен для HQL-запроса

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }


    @Transactional(readOnly = true)
    @Override
    public User getUserByCarModelAndSeries(String model, int series) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery(
                        "FROM User u " +
                                "WHERE u.car.model = :model AND u.car.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
    }
}