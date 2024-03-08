import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FakeApiService : ApiService {

    private val fakeService = FakeService()

    override fun getOffers(): Call<List<Offer>> {
        val offers = fakeService.getOffers()
        return object : Call<List<Offer>> {
            override fun enqueue(callback: Callback<List<Offer>>) {
                callback.onResponse(this, Response.success(offers))
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun clone(): Call<List<Offer>> {
                return this
            }

            // Другие методы интерфейса, которые необходимо реализовать
        }
    }
}
