import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : 
 * @date   : 
 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{
		// compl�ter

		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).Negative();
			}
		}

	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toBWPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toGrayPixel();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toColorPixel();
			}
		}
		
	}
	
	public void convertToTransparentImage()
	{
		// compl�ter
		for (int i=0; i< this.height; i++) {
			for(int j=0; j< this.width; j++)
			{
				this.imageData[i][j] = (this.imageData[i][j]).toTransparentPixel();
			}
		}
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compléter
		int xr=0,yr=0;
		PixelMapPlus temp=new PixelMapPlus(this.imageType,height,width);
		for(int i=0;i<temp.height;i++)
			for(int j=0;j<temp.width;j++) {
				temp.imageData[i][j]=this.imageData[i][j];
				imageData[i][j]= new BWPixel(true);
			}
		System.out.println("hello");
		for(int i=0;i<temp.height;i++)
			for(int j=0;j<temp.width;j++) {
				yr=(int) ((i-x)*Math.cos(angleRadian) +(y-j)*Math.sin(angleRadian)+x);
				xr=(int)((j-y)*Math.cos(angleRadian) +(i-x)*Math.sin(angleRadian)+y);
				if( yr>=0 && yr<height && xr>=0 && xr<width)
					imageData[i][j]=temp.imageData[yr][xr];

			}


	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
		// compl�ter
		//1.Declaration de la nouvelle map
		AbstractPixel[][] rescaledImage = new AbstractPixel[h][w];
		//2.Calcul de l'échelle selon chaque axe
		double scaleH = (double)this.height/(double)h;
		double scaleW = (double)this.width/(double)w;
		//3.Algorithme: De mise à l'échelle
		for(int row = 0; row < h; row++){
			for(int col = 0; col < w ; col++){

				rescaledImage[row][col] = this.imageData[(int)(((double)row)*scaleH)][(int)(((double)col)*scaleW)];
			}
		}

		//4. Désallocation de l'image actuelle
		this.clearData();

		//5. Copie profonde:
		height = h;
		width = w;
		imageData = new AbstractPixel[height][width];

		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = rescaledImage[row][col];
			}
		}
		// On clear comme on a plus besoin
		rescaledImage = null;

	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{
		// compl�ter
		//1.Vérifier que les dimensions de la matrice ne seront pas dépacés
		int rowMax = ((row0 + pm.height)< this.height) ? (row0 + pm.height):this.height;
		int colMax = ((col0 + pm.width)< this.width) ? (col0 + pm.width):this.width;
		//2.Copier la matrice pm dans l'image actuelle
		for(int row=row0; row<rowMax; row++)
		{
			for(int col = col0; col < colMax; col++)
			{
				this.imageData[row][col] = pm.imageData[row-row0][col-col0];
			}
		}

	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compl�te
		//1. Allocation de mémoire pour l'image croped

		AbstractPixel[][] cropedImage = new AbstractPixel[h][w];
		//Par défaut ca creer une image blanche
		for(int row=0; row<h; row++)
		{
			for(int col = 0; col < w; col++)
			{
				cropedImage[row][col] = new ColorPixel();
			}
		}
		//2. Copier à partir du pixel en haut à gauche (0,0), la matrice originale dans
		// la matrice croped

		//2.1.Vérifier que les dimensions de la matrice ne seront pas dépacés
		// ==> cas 1: La dimension de la cropedImage est plus petite que celle de l'objet courant
		//         ==> On ne recopie que jusqu'à la dimension de la cropedImage
		// ==> cas 2: La dimension de la cropedImage est plus grande que celle de l'objet courant
		//         ==> On ne recopie que jusqu'à la dimension de l'objet courant

		int rowMax = (this.height < h) ? this.height:h;
		int colMax = (this.width < w) ? this.width:w;
		//2.2.Copier la matrice pm dans l'image actuelle
		for(int row=0; row<rowMax; row++)
		{
			for(int col = 0; col < colMax; col++)
			{
				cropedImage[row][col] = this.imageData[row][col];
			}
		}
		//3. Desallocation de la matrice actuelle
		this.clearData();
		//4. Copie profonde:
		height = h;
		width = w;
		imageData = new AbstractPixel[height][width];

		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = cropedImage[row][col];
			}
		}
		// On clear comme on a plus besoin
		cropedImage = null;

	}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compl�ter		
		//1. Allocation de mémoire pour l'image translatée (qui aura les mêmes dimensions
		// que la première

		AbstractPixel[][] shiftedImage = new AbstractPixel[height][width];

		//2. Par défaut on remplit avec des pixels blancs
		for(int row=0; row<height; row++)
		{
			for(int col = 0; col < width; col++)
			{
				shiftedImage[row][col] = new ColorPixel(); // blancs par défaut
			}
		}

		// 2. Définir les frontières de la zone à copier
		int row0 = (rowOffset > 0) ? rowOffset: 0;
		int col0 = (colOffset > 0) ? colOffset: 0;
		int rowMax = (rowOffset > 0) ? height : (height + rowOffset); //Rappel le offset est négatif
		int colMax = (colOffset > 0)  ? width : (width + colOffset);

		//2.2.Copier la matrice pm dans l'image actuelle
		for(int row=row0; row<rowMax; row++)
		{
			for(int col = col0; col < colMax; col++)
			{

				shiftedImage[row-rowOffset][col-colOffset] = this.imageData[row][col];
			}
		}
		//3. Desallocation de la matrice actuelle
		this.clearData();
		//4. Copie profonde:

		imageData = new AbstractPixel[height][width];

		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
			{
				imageData[row][col] = shiftedImage[row][col];
			}
		}
		// On clear comme on a plus besoin
		shiftedImage = null;
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compl�ter

		// Crop de l'image autour du point d'intérêt:
		//1. La nouvelle coupée aura les dimensions suivantes (en théorie):
		int h = (int)((double)height/zoomFactor);
		int w = (int)((double)width/zoomFactor);

		// 2. On doit vérifier si c'est hors des frontières de l'image:

		int row0;
		int col0;

		if((y - h/2) < 0){
			row0 = 0;
		} else if((y + h/2)> height){
			row0 = height - h;
		} else {
			row0 =  y - h/2;
		}
		if((x - w/2) < 0){
			col0 = 0;
		} else if((x + w/2)> width){
			col0 = width - w;
		} else {
			col0 =  x - w/2;
		}
		//3. Définir l'image centrée autour du point (x,y)

		AbstractPixel[][] cropedImage = new AbstractPixel[h][w];

		//Recopiage de la région concernée
		for(int row = row0; row< (row0 + h); row++)
		{
			for(int col = col0; col < (col0 + w); col++)
			{
				cropedImage[row - row0][col - col0] = this.imageData[row][col];
			}
		}

		//4. Desallocation de la matrice actuelle
		this.clearData();


		//5. Copie profonde:

		height = h;
		width = w;
		imageData = new AbstractPixel[height][width];

		for(int row=0; row<height; row++)
		{
			for(int col=0; col<width; col++)
				{
				imageData[row][col] = cropedImage[row][col];
				}
		}
		// On clear comme on a plus besoin
		cropedImage = null;

		//6. Resize ( retour à la taille original en extrapolant pour avoir l'effet du zoom)
		this.resize( (int)((double)w*zoomFactor), (int)((double)h*zoomFactor));

	}
}
