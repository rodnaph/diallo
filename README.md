
# Badges for Jenkins

Diallo is a small Compojure web application that displays 'badges' for your Jenkins jobs. It's
meant to be a coderwall-style view of your Jenkins setup, so you can easily spot where you
might be overlooking required plugins or settings.

## Usage

Clone the repository then...

```
export JENKINS_URL=http://somedomain.com
export JENKINS_USER=login-required
export JENKINS_TOKEN=alongtokenhereifthisisneeded

lein run
```

(Diallo uses [Jenko](https://github.com/rodnaph/jenko) to talk to Jenkins)

You can then navigate to http://localhost:1234, and you'll get a list of the views that you
have in your Jenkins instance...

![](http://github.com/rodnaph/diallo/raw/master/screenshots/index.png)

Then click on any of the views to move to see all the jobs in that view, with badges
for each of the 'features' that they have enabled.

![](http://github.com/rodnaph/diallo/raw/master/screenshots/view.png)

## Features

The features listed for each job is a combination of the build steps and the build publishers
that are configured fro that job.

