package ru.lab2.personservice.repostirory;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.ext.Provider;

import ru.lab2.library.Color;
import ru.lab2.library.Person;

import java.util.ArrayList;
import java.util.List;

@Provider
@Stateless
public class PersonRepository {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public Person findById(int id) {
        return em.find(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        em.persist(person);
        em.flush();
    }

    @Transactional
    public Person update(Person person) {
        return em.merge(person);
    }

    @Transactional
    public void delete(int id) {
        em.remove(findById(id));
    }

    public List<Person> findAllPersonsByFilter(int pageNumber,
                                         int pageSize,
                                         Integer id,
                                         String name,
                                         Double coordinateX,
                                         Double coordinateY,
                                         String creationDate,
                                         Double height,
                                         String birthday,
                                         String passportId,
                                         String eyeColor,
                                         Integer locationX,
                                         Double locationY,
                                         Float locationZ) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> person = criteriaQuery.from(Person.class);
        List<Predicate> predicates = new ArrayList<>();

        if (id != null) {
            predicates.add(builder.equal(person.get("id"), id));
        }
        if (name != null) {
            predicates.add(builder.like(person.get("name"), "%" + name + "%"));
        }
        if (coordinateX != null) {
            predicates.add(builder.equal(person.get("coordinates").get("x"), coordinateX));
        }
        if (coordinateY != null){
            predicates.add(builder.equal(person.get("coordinates").get("y"), coordinateY));
        }
        if (creationDate != null) {
            predicates.add(builder.equal(person.get("creationDate"), creationDate));
        }
        if (height != null) {
            predicates.add(builder.equal(person.get("height"), height));
        }
        if (birthday != null) {
            predicates.add(builder.equal(person.get("birthday"), birthday));
        }
        if (passportId != null) {
            predicates.add(builder.equal(person.get("passportId"), passportId));
        }
        if (eyeColor != null) {
            predicates.add(builder.equal(person.get("eyeColor"), Color.valueOf(eyeColor)));
        }
        if (locationX != null) {
            predicates.add(builder.equal(person.get("location").get("x"), locationX));
        }
        if (locationY != null) {
            predicates.add(builder.equal(person.get("location").get("y"), locationY));
        }
        if (locationZ!=null){
            predicates.add(builder.equal(person.get("location").get("z"), locationZ));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Person> query = em.createQuery(criteriaQuery);
        query.setFirstResult((pageNumber -1)*pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public Long countByEyeColorLessThan(String eyeColor) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<Person> person = criteriaQuery.from(Person.class);
        Predicate eyeColorPredicate = builder.lessThan(person.get("eyeColor"), Color.valueOf(eyeColor));

        criteriaQuery.select(builder.count(person)).where(eyeColorPredicate);
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    public List<Person> filterByEyeColorLessThan(String eyeColor){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> person = criteriaQuery.from(Person.class);
        Predicate eyeColorPredicate = builder.lessThan(person.get("eyeColor"), Color.valueOf(eyeColor));

        criteriaQuery.select(person).where(eyeColorPredicate);
        return em.createQuery(criteriaQuery).getResultList();
    }

    public List<Person> filterByHeightMoreThan(Double height){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
        Root<Person> person = criteriaQuery.from(Person.class);
        Predicate heightPredicate = builder.greaterThan(person.get("height"), height);

        criteriaQuery.select(person).where(heightPredicate);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
