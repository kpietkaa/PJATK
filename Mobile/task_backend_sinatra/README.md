# Task Backend Sinatra ![Heroku](https://heroku-badge.herokuapp.com/?app=heroku-badge)

App wrote in [Sinatra](https://github.com/sinatra/sinatra) (simple and lightweight web framework written in Ruby)
to provide backend for mobile application. Url:

    https://task-backend-sinatra.herokuapp.com/

## Instalation & Development

Clone, install dependencies, setup database (`config/environments.rb`), run migrations, add initial data, start server

* `git clone https://github.com/kpietkaa/PJATK.git task_backend_sinatra`
* `bundle install`
* `rake db:create`
* `rake db:migrate`
* `rake db:seed`
* `shotgun config.ru`

Go to `http://localhost:9393/` for check.

## Usage

### Create a task (post)

    curl -d "name=task&price=10&qunatity=1" http://localhost:9393/tasks

`name` and `price` and `qunatity` are required parameters.

### Read all tasks (get)

    curl http://localhost:9393/tasks

### Read one task (get)

    curl http://localhost:9393/tasks/:id

### Update a task (update)

    curl -X PUT -d "name=test&price=666" http://localhost:9393/tasks/:id

### Delete a task (delete)

    curl -X DELETE http://localhost:9393/tasks/:id

### Clear DB and fill with seeds

    curl http://localhost:9393/refresh
