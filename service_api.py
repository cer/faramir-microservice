from phone_number_parser import parse

from fastapi import FastAPI
from fastapi.responses import JSONResponse
from pydantic import BaseModel

app = FastAPI()

class ParsePhoneNumberRequest(BaseModel):
    phoneNumber: str

@app.post("/parse")
def parseNumber(request: ParsePhoneNumberRequest):
    result = parse(request.phoneNumber)
    if result == "FAIL":
        return JSONResponse(status_code=400)
    else:
        return {"result": result}
