import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProfileService: ProfileServiceRepository {
    override fun asyncFetchByName(name: String) = GlobalScope.async {
        Profile(1, name, 28);
    }

    override fun asyncFetchById(id: Long) = GlobalScope.async {
        Profile(id, "Roach", 30);
    }
}