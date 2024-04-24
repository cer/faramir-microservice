from phone_number_parser import parse

def test_valid_number():
   assert parse("5105551212") == "(510) 555-1212"

def test_valid_number_spaces():
   assert parse("510 555 1212") == "(510) 555-1212"

def test_valid_number_formatted():
   assert parse("(510) 555-1212") == "(510) 555-1212"

def test_bad_number():
   assert parse("510555121") == "FAIL"
    