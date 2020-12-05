# -*- coding: utf-8 -*-
"""
Created on Sat Dec  5 13:28:55 2020

@author: win10
"""

import pandas as pd
import numpy as np
import pickle

data = pd.read_csv('final priority dataset preprocessed')
X = data[['Gender','Age','Diseases','Zone','Profession']]
y = data['Priority']

from sklearn.ensemble import RandomForestClassifier
clf=RandomForestClassifier(n_estimators=100)
clf.fit(X,y)

#print(clf.predict([[1,22,9,2,1]]))
#To check the model

# Saving model to disk
pickle.dump(clf, open('prior.pkl','wb'))

# Loading model to compare the results
model = pickle.load(open('prior.pkl','rb'))
print(model.predict([[1,22,9,2,1]]))