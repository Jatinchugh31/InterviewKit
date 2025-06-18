we define jenkinsFile in our code base which call
@Library("ci-cd-library") _
buildApp()
 

for CI CD we two separate YML files, 
Which are read by Jenkins  --  default-quarkus 

for The CI part 
we define the Yml file  , in YMl file we deinfne our branching strategies     
for which branch whcih action we need to do 
  example
id: default-quarkus
branches:
- name: PR-.*
  actions:
    - type: quarkus-gradle-build
      publish: true
      run-tests: true
      gradle-version: 8.4
      sonarqube: true
      xray: true
      target-env - dev-2
- name: master
so this will first do the build 
- the publish the image 
- run test 
- sonar
- target env etc 

so for the same Cd we have deployment file   id: default-quarkus

in which we defne target env luke which chat it wil use  
spinakker tamplate   , how much resource it will use  , other properties 

deployments:
- target-env: dev-1
  chart:
  name: quarkus-app
  version: 1.1.3
  spinnaker-template:
  id: default-cd-cluster
  version: latest
  values:
  elasticApm:
  enabled: true
  server: http://apm-server-1-apm-server.monitoring:8200
  envs:
  CASSANDRA_ENV: dev1
  QUARKUS_SMALLRYE_OPENAPI_PATH: /q/openapi
  QUARKUS_SMALLRYE_OPENAPI_APP_PATH: /q/swagger-ui
  resources:
  requests:
  cpu: 50m
  memory: 50Mi
  limits:
  cpu: 1
  memory: 1Gi
  hpa:
  minReplicas: 1
  maxReplicas: 2
  targetCPUUtilizationPercentage: 80
  deployment-type: download


so all these file will be read by jenkins server . 

we can do further configuration for each jenkins and jenkis will read the above properties 

we can add plugins and webhook in jenkins. 



example of stages in , 


def call() {
def branchName = env.BRANCH_NAME
def config = readYaml file: "default-quarkus.yml"

    def matchingBranch = config.branches.find { branchName ==~ it.name }

    if (!matchingBranch) {
        error "No pipeline actions defined for branch: ${branchName}"
    }

    def actions = matchingBranch.actions

    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build') {
                when {
                    expression { actions.any { it.type == "quarkus-gradle-build" } }
                }
                steps {
                    sh './gradlew clean build'
                }
            }

            stage('Run Tests') {
                when {
                    expression { actions.any { it['run-tests'] } }
                }
                steps {
                    sh './gradlew test'
                }
            }

            stage('SonarQube Scan') {
                when {
                    expression { actions.any { it['sonarqube'] } }
                }
                steps {
                    withSonarQubeEnv('MySonarServer') {
                        sh './gradlew sonarqube'
                    }
                }
            }

            stage('Xray Security Scan') {
                when {
                    expression { actions.any { it['xray'] } }
                }
                steps {
                    echo 'Running Xray Scan...'
                    // Add your Xray integration CLI here
                }
            }

            stage('Publish Docker Image') {
                when {
                    expression { actions.any { it['publish'] } }
                }
                steps {
                    sh 'docker build -t my-app:${env.BUILD_NUMBER} .'
                    sh 'docker push my-app:${env.BUILD_NUMBER}'
                }
            }

            stage('Trigger Deployment') {
                when {
                    expression { actions.any { it['target-env'] } }
                }
                steps {
                    echo "Triggering Spinnaker deploy to ${actions[0]['target-env']}..."
                    // Trigger Spinnaker pipeline via webhook or CLI
                }
            }
        }
    }
}
