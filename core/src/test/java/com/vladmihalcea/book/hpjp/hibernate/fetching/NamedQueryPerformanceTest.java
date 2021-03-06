package com.vladmihalcea.book.hpjp.hibernate.fetching;

import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * <code>NamedQueryPerformanceTest</code> - Named Query Performance Test
 *
 * @author Vlad Mihalcea
 */
public class NamedQueryPerformanceTest extends CreateQueryPerformanceTest {

    public static final String QUERY_NAME_1 = "findPostCommentSummary";
    public static final String QUERY_NAME_2 = "findPostComments";

    public NamedQueryPerformanceTest(int planCacheMaxSize) {
        super(planCacheMaxSize);
    }

    @Override
    public void init() {
        super.init();
        doInJPA(entityManager -> {
            entityManagerFactory().addNamedQuery(QUERY_NAME_1, createQuery1(entityManager));
            entityManagerFactory().addNamedQuery(QUERY_NAME_2, createQuery2(entityManager));
        });
    }

    @Override
    protected Object getQuery1(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        return session.getNamedQuery(QUERY_NAME_1);
    }

    @Override
    protected Object getQuery2(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        return session.getNamedQuery(QUERY_NAME_2);
    }
}
