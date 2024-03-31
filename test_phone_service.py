from fastapi.testclient import TestClient
from faramir import app

client = TestClient(app)

def test_valid_phone_number():
    response = client.post(
        "/parse",
        json={"phoneNumber": "5105551212"}
    )
    assert response.status_code == 200
    assert response.json()['result'] == "(510) 555-1212"

def test_invalid_phone_number():
    response = client.post(
        "/parse",
        json={"phoneNumber": "51055512"}
    )
    assert response.status_code == 400