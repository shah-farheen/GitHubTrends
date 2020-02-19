package com.buggyarts.hubtrends.test

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        if (request.path.equals("repositories")) {
            return MockResponse()
                .setResponseCode(200)
                .setBody(TestUtils.HAPPY_RESPONSE_BODY)
        }
        return MockResponse()
            .setResponseCode(404)
            .setBody("{}")
    }
}