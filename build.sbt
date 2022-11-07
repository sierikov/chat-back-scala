scalaVersion := "3.1.3"
name         := "chat-back-zio"

libraryDependencies ++= Seq(
  "dev.zio"       %% "zio"                  % "2.0.2",
  "dev.zio"       %% "zio-json"             % "0.3.0-RC11",
  "dev.zio"       %% "zio-config"           % "3.0.2",
  "dev.zio"       %% "zio-config-typesafe"  % "3.0.2",
  "dev.zio"       %% "zio-config-magnolia"  % "3.0.2",
  "dev.zio"       %% "zio-logging"          % "2.1.2",
  "io.d11"        %% "zhttp"                % "2.0.0-RC10",
  "io.getquill"   %% "quill-zio"            % "4.6.0",
  "io.getquill"   %% "quill-jdbc-zio"       % "4.6.0",
  "com.h2database" % "h2"                   % "2.1.214",
  "org.slf4j"      % "slf4j-simple"         % "2.0.3"
)
