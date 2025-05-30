Here are some common Docker commands you'll use for building, running, and managing containers:

### 1. **Docker Commands for Working with Images:**

* **Build an Image from a Dockerfile**:

  ```bash
  docker build -t <image_name>:<tag> <path_to_dockerfile>
  ```

  Example:

  ```bash
  docker build -t myapp:v1 .
  ```

* **List Docker Images**:

  ```bash
  docker images
  ```

* **Remove an Image**:

  ```bash
  docker rmi <image_name>:<tag>
  ```

  Example:

  ```bash
  docker rmi myapp:v1
  ```

* **Pull an Image from Docker Hub**:

  ```bash
  docker pull <image_name>:<tag>
  ```

  Example:

  ```bash
  docker pull ubuntu:latest
  ```

### 2. **Docker Commands for Running Containers:**

* **Run a Container**:

  ```bash
  docker run -d --name <container_name> <image_name>:<tag>
  ```

  Example:

  ```bash
  docker run -d --name myapp-container myapp:v1
  ```

    * `-d`: Run the container in the background (detached mode).
    * `--name`: Assign a name to your container.

* **Run a Container with Ports Mapping**:

  ```bash
  docker run -d -p <host_port>:<container_port> --name <container_name> <image_name>:<tag>
  ```

  Example:

  ```bash
  docker run -d -p 8080:80 --name myapp-container myapp:v1
  ```

* **Run a Container Interactively**:

  ```bash
  docker run -it <image_name>:<tag> bash
  ```

  Example:

  ```bash
  docker run -it ubuntu:latest bash
  ```

### 3. **Docker Commands for Managing Containers:**

* **List Running Containers**:

  ```bash
  docker ps
  ```

* **List All Containers (including stopped ones)**:

  ```bash
  docker ps -a
  ```

* **Stop a Running Container**:

  ```bash
  docker stop <container_name_or_id>
  ```

  Example:

  ```bash
  docker stop myapp-container
  ```

* **Start a Stopped Container**:

  ```bash
  docker start <container_name_or_id>
  ```

* **Remove a Container**:

  ```bash
  docker rm <container_name_or_id>
  ```

* **View Logs of a Container**:

  ```bash
  docker logs <container_name_or_id>
  ```

* **Execute a Command Inside a Running Container**:

  ```bash
  docker exec -it <container_name_or_id> <command>
  ```

  Example:

  ```bash
  docker exec -it myapp-container bash
  ```

### 4. **Docker Commands for Networking and Volumes:**

* **Create a Docker Network**:

  ```bash
  docker network create <network_name>
  ```

* **Connect a Container to a Network**:

  ```bash
  docker network connect <network_name> <container_name_or_id>
  ```

* **Create a Volume**:

  ```bash
  docker volume create <volume_name>
  ```

* **Mount a Volume to a Container**:

  ```bash
  docker run -d -v <volume_name>:/<container_path> <image_name>:<tag>
  ```

### 5. **Docker Compose Commands (if using Docker Compose for multi-container setups):**

* **Start Containers Defined in `docker-compose.yml`**:

  ```bash
  docker-compose up
  ```

* **Start Containers in Detached Mode**:

  ```bash
  docker-compose up -d
  ```

* **Stop Running Containers**:

  ```bash
  docker-compose down
  ```

* **Build Containers Defined in `docker-compose.yml`**:

  ```bash
  docker-compose build
  ```

These commands will help you to manage your Docker environment and work with containers effectively!
