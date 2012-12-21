
# Badges for Jenkins

Diallo is a small [Compojure](https://github.com/weavejester/compojure) web application that
displays 'badges' for your [Jenkins](http://jenkins-ci.org) jobs. It's meant to be a simple
[Coderwall](http://coderwall.com) style view of your setup, so you can easily spot where you
might be overlooking required plugins or settings.

## Usage

To get started you'll just need [Leiningen](http://leiningen.org/).  Then clone the repository and...

```
# required URL to the jenkins instance
export JENKINS_URL=http://somedomain.com

# if your jenkins instance requires login
export JENKINS_USER=if-login-required
export JENKINS_TOKEN=ALongTokenHereButOnlyIfLoginIsRequired

# start the web server
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
that are configured on that job.  It's a bit squiffy and random, but if you've got any ideas
for nice icons for visualising particular things just open an issue!  :D

