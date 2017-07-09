package administration.jpa.daos;


import java.util.Collection;
import java.util.List;

/**
 * Created by Max on 28.05.2017.
 */
    /*
        Common Interface for all of our Dao classes

        Completely hides all the JPA/Database related stuff.

        Always use this interface as the reference type wherever you
        need to use the Dao!
     */
public interface IGenericDao<T extends AbstractDatabaseEntity>
{
    T findById( Long id );
    Collection<T> findAll();

    void create( T entity );
    void createAll( Collection<T> newEntities );

    T update( T entity );

    void delete( Long id );
    void delete( T entity );
    void delete( List<T> entries );
}
