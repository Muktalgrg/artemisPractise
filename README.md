Create master and slave broker:

Inside artemis/bin> ./artemis create /Users/batman/software/brokers/master-broker --http-port 8161

Inside artemis/bin > ./artemis create /Users/batman/software/brokers/slave-broker --http-port 8162


or with command

./artemis create –-replicated –-clustered master 
./artemis create –-replicated –-clustered slave
