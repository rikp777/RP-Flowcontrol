gource \
  --seconds-per-day .4 \
  -1280x720 \
  --auto-skip-seconds .1 \
  --multi-sampling \
  --stop-at-end \
  --key \
  --highlight-users \
  --date-format "%d/%m/%y" \
  --hide mouse \
  --file-idle-time 0 \
  --max-files 0  \
  --background-colour 000000 \
  --font-size 25 \
  --title "Flowcontrol" \
  --output-ppm-stream - \
  --output-framerate 30 \
  | ffmpeg -y -r 30 -f image2pipe -vcodec ppm -i - -b 65536K flowcontrol.mp4