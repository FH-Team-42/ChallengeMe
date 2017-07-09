package administration.jpa.daos;

/**
 * Created by Max on 28.05.2017.
 */


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;



/*
    Our generic Dao for all model objects

    It has package-local visibility - thus, instances can only be created inside
    this package. Using this approach, we can prevent any class from the outside of
    this package to create Dao instances.

    Anyone, who needs a Dao must therefore use our DataController class.

 */
class GenericDao<T extends AbstractDatabaseEntity> implements IGenericDao<T>
{
    private final Class<T> persistentClass;
    private EntityManager entityManager;

    public GenericDao(Class<T> type, EntityManager em )
    {
        this.persistentClass = type;
        this.entityManager = em;
    }

    public T findById( final Long id )
    {
        final T result = getEntityManager().find( persistentClass, id );
        return result;
    }

    public Collection<T> findAll()
    {
        Query query = getEntityManager().createQuery(
                "SELECT e FROM " + getEntityClass().getCanonicalName() + " e" );
        return (Collection<T>) query.getResultList();
    }

    public void create( T entity )
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist( entity );
        getEntityManager().getTransaction().commit();
    }

    public void createAll( Collection<T> newEntities )
    {
        getEntityManager().getTransaction().begin();

        for( T entry : newEntities )
            getEntityManager().persist( entry );

        getEntityManager().getTransaction().commit();
    }

    public T update( T entity )
    {
        getEntityManager().getTransaction().begin();
        final T savedEntity = getEntityManager().merge( entity );
        getEntityManager().getTransaction().commit();

        return savedEntity;
    }

    public void delete( Long id )
    {
        T entity = this.findById( id );
        this.delete( entity );
    }

    public void delete( T entity )
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove( entity );
        getEntityManager().getTransaction().commit();
    }

    public void delete( List<T> entries )
    {
        getEntityManager().getTransaction().begin();

        for( T entry : entries )
        {
            getEntityManager().remove( entry );
        }

        getEntityManager().getTransaction().commit();
    }

/*
    Getter & Setter
 */

    public Class<T> getEntityClass()
    {
        return persistentClass;
    }

    public void setEntityManager( final EntityManager entityManager )
    {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager()
    {
        return entityManager;
    }

}


