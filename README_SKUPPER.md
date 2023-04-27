With this simple procedure we will create a shared service within cluster to join applications

References: https://github.com/skupperproject/skupper-example-hello-world

Pre-requisites:
login in cluster a
mv config config_a

Do the same for cluster b

In console for cluster A
export KUBECONFIG=~/.kube/config_a

Procedure


On cluster A, create two namespaces: 
store and prices.

On cluster B, create the namespace:
prices. 

Deploy the quote-service in cluster A, project store
mvn clean install -Dquarkus.kubernetes.deploy=true

Deploy the prices-service in cluster A and B, in project prices
From console A in prices-service folder project
mvn clean install -Dquarkus.kubernetes.deploy=true
From console B in prices-service folder project
mvn clean install -Dquarkus.kubernetes.deploy=true

skupper init --site-name prices1 --enable-console --enable-flow-collector
skupper token create a-to-b-token.yaml
skupper service create prices-balanced-svc 8080 --mapping http
skupper service bind prices-balanced-svc deploymentconfig prices-service
skupper status
kubectl get secret/skupper-console-users -o jsonpath={.data.admin} | base64 -d
releease price service mvn clean install -Dquarkus.kubernetes.deploy=true

In console for cluster B
export KUBECONFIG=~/.kube/config_b
skupper init --site-name prices2
skupper link create a-to-b-token.yaml
skupper service bind prices-balanced-svc deploymentconfig prices-service

releease price service mvn clean install -Dquarkus.kubernetes.deploy=true

