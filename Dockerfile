FROM openjdk:11

LABEL source="https://github.com/coelhocaique/personal-finance-gateway"\
      maintainer="Caique Coelho"

ADD ./build/distributions/*.zip /personal-finance-gateway.zip

RUN unzip personal-finance-gateway.zip && \
   rm -rf *.zip && \
   mv personal-finance-gateway-* personal-finance-gateway

EXPOSE 80 443

ENTRYPOINT [ "/personal-finance-gateway/bin/personal-finance-gateway" ]
