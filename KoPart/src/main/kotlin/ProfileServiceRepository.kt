import kotlinx.coroutines.Deferred

interface ProfileServiceRepository {
    suspend fun findByName(name: String): Profile
    suspend fun findById(id: Long): Profile
}