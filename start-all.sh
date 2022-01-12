{
  cd backend && ./gradlew clean bR &
  cd ../proxy && ./gradlew clean bR &
  cd ../web && npm start
}