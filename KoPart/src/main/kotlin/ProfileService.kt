import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProfileService: ProfileServiceRepository {
    override suspend fun findByName(name: String): Profile {
        return Profile(1, name, 28);
    }

    override suspend fun findById(id: Long): Profile {
        return Profile(id, "Roach", 30);
    }
}