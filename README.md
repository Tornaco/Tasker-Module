# Tasker
Running tasks based on any uiautomator API through this APP on Root devices

## Functional design

There are 2 parts to implement this goal:
1. Main App, accept user tasks and route these commands to test suit app.
2. Test suit app, based on android junit test and uiautomator framework.

On a normal test step, we usually start a test like this:
```
am instrument -w -r -e package xxx -e debug false xxxx/android.support.test.runner.AndroidJUnitRunner
```

So, we execute this command on our app, then the test suit can get the tasks(param, data) via AIDL service.
