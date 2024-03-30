from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model='gpt-4')

def parse(input):
    result = llm.invoke(f"""
Instruction: 
What follows is a phone number.
Validate the phone number
If it is a valid US phone number print it in the standard format, eg (205) 555-1212.
Otherwise print FAIL. 
Print only the phone number or FAIL. 
No other words                    

phone number:
{input}
 """)
     # "token_usage" : result.response_metadata['token_usage']}
    return result.content
