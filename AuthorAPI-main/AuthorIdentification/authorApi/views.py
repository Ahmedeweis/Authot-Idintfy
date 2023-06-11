from django.shortcuts import render
from django.http import HttpResponse
###########################################
from django.conf import settings
import os
import joblib

import json
# Create your views here.

def predictAuthor(request):



    
    article=request.GET.get('article','mahmoud')
    print('article:= '+article)
  
    pklFile= os.path.join(settings.DATA_DIR, 'trained_model.pkl')
    loaded_model = joblib.load(pklFile)

    author =loaded_model.predict([article])
    print(author)
    data = {'author':author[0],'article':article}
    json_data = json.dumps(data)
    response = HttpResponse(json_data, content_type='application/json')
    response['Cache-Control'] = 'no-cache'
    response.status_code = 200
    ###################################################
    return response


