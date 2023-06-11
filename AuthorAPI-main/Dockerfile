from python:3.9

ENV PYTHONUNBUFFERED=1

workdir /codeProject

copy requirements.txt requirements.txt

run pip install -r requirements.txt

copy . .

cmd ["python" ,"AuthorIdentification/manage.py", "runserver", "0.0.0.0:8000"]